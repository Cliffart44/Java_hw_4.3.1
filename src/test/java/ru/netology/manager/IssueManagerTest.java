package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.IssueComparator;
import ru.netology.repository.IssueRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {
    int nonexistentID = 4;
    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);
    IssueComparator c = new IssueComparator();
    Issue first = new Issue(1, 20, false, "jsedding", "Task", "Master");
    Issue second = new Issue(2, 5, false, "paschi", "Bug", "Junior");
    Issue third = new Issue(3, 10, true, "paschi", "Bug", "Master");

    @BeforeEach
    void setUp() {
        manager.addAll(List.of(first, second, third));
    }

    /* Добавление Issue */

    @Test
    public void shouldAddOne() {
        Issue fourth = new Issue(4, 7, true, "jsedding", "Bug", "Master");
        manager.add(fourth);
        Collection<Issue> expected = List.of(first, second, third, fourth);
        Collection<Issue> actual = manager.getAll();
        assertEquals(expected, actual);
    }

    /* Отображение списка открытых и закрытых Issue (два отдельных метода) */

    @Test
    public void shouldShowOpenIssues() {
        Collection<Issue> expected = List.of(first, second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowClosedIssues() {
        Collection<Issue> expected = List.of(third);
        Collection<Issue> actual = manager.showClosedIssues();
        assertEquals(expected, actual);
    }

    /* Фильтрация (3 отдельных метода):
    По имени автора (кто создал)
    По Label'у
    По Assignee (на кого назначено) */

    @Test
    public void shouldFilterByAuthor() {
        Collection<Issue> expected = List.of(second, third);
        Collection<Issue> actual = manager.filterByAuthor("paschi");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByLabel() {
        Collection<Issue> expected = List.of(second, third);
        Collection<Issue> actual = manager.filterByLabel("Bug");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAssignee() {
        Collection<Issue> expected = List.of(first, third);
        Collection<Issue> actual = manager.filterByAssignee("Master");
        assertEquals(expected, actual);
    }

    /* Сортировка */

    @Test
    public void shouldSortByCommentsQuantity() {
        Collection<Issue> expected = List.of(first, third, second);
        Collection<Issue> actual = manager.sortByCommentsQuantity();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortByCommentsQuantityReversed() {
        Collection<Issue> expected = List.of(second, third, first);
        Collection<Issue> actual = manager.sortByCommentsQuantityReversed(c);
        assertEquals(expected, actual);
    }

    /* Закрытие/открытие Issue по ID */

    @Test
    public void shouldOpenIssue() {
        manager.openIssue(3);
        Collection<Issue> expected = List.of(first, second, third);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCloseIssue() {
        manager.closeIssue(1);
        Collection<Issue> expected = List.of(first, third);
        Collection<Issue> actual = manager.showClosedIssues();
        assertEquals(expected, actual);
    }

    /* Тесты для покрытия */

    @Test
    public void shouldNotOpenIssue() {
        manager.openIssue(nonexistentID);
        Collection<Issue> expected = List.of(first, second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotCloseIssue() {
        manager.closeIssue(nonexistentID);
        Collection<Issue> expected = List.of(first, second);
        Collection<Issue> actual = manager.showOpenIssues();
        assertEquals(expected, actual);
    }
}