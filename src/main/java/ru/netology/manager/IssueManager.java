package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.NotFoundException;
import ru.netology.repository.IssueRepository;

import java.util.*;

public class IssueManager {
    IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    /* Добавление Issue */

    public void add(Issue item) {
        repository.save(item);
    }

    /* Отображение списка открытых и закрытых Issue (два отдельных метода) */

    public Collection<Issue> showOpenIssues() {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if ((!item.isClosed())) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Issue> showClosedIssues() {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.isClosed()) {
                result.add(item);
            }
        }
        return result;
    }

    /* Фильтрация (3 отдельных метода):
    По имени автора (кто создал)
    По Label'у
    По Assignee (на кого назначено) */

    public Collection<Issue> filterByAuthor(String author) {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.matchesAuthor(author)) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Issue> filterByLabel(String label) {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.matchesLabel(label)) {
                result.add(item);
            }
        }
        return result;
    }

    public Collection<Issue> filterByAssignee(String assignee) {
        Collection<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.matchesAssignee(assignee)) {
                result.add(item);
            }
        }
        return result;
    }

    /* Сортировка */

    public Collection<Issue> sortByCommentsQuantity() {
        ArrayList<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            result.add(item);
        }
        Collections.sort(result);
        return result;
    }

    public Collection<Issue> sortByCommentsQuantityReversed(Comparator<Issue> reversed) {
        ArrayList<Issue> result = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            result.add(item);
        }
        Collections.sort(result, reversed);
        return result;
    }

    /* Закрытие/открытие Issue по ID */

    public void openIssue(int id) {
        try {
            repository.openIssue(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeIssue(int id) {
        try {
            repository.closeIssue(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Вспомогательные методы*/

    public void addAll(Collection<Issue> items) {
        repository.saveAll(items);
    }

    public Collection<Issue> getAll() {
        return repository.findAll();
    }
}