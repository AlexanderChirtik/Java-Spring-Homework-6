package com.example.node_list.service;

import com.example.node_list.model.Node;
import com.example.node_list.repository.NodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс производит подключение к базе данных и использует методы репозитоия NodeRepository для работы с ней.
 */
@Service
@RequiredArgsConstructor
public class NodeService {

    /**
     * Объект интерфейса NodeRepository для соединения с БД.
     * Создается автоматически благодаря аннотации @RequiredArgsConstructor.
     */
    private final NodeRepository repository;

    /**
     * Метод добавления записи в базу данных.
     * @param node Принимает новую запись из тела POST запроса.
     */
    public void addNode(Node node) {
        repository.save(node);
    }

    /**
     * Метод демонстрирует все записи из базы данных.
     * @return Возвращает список всех записей.
     */
    public List<Node> getAllNode() {
        return repository.findAll();
    }

    /**
     * Метод находит запись по её порядковому номеру в БД.
     * @param id Принимает номер записи.
     * @return Возвращает запись, если она есть в БД. Или возвращает null, есть такой запси нет.
     */
    public Node getNodeById(Long id) {
        return repository.findById(id).orElseThrow(null);
    }

    /**
     * Метод обновляет запись в базе данных, находя её по номеру.
     * @param id Принимает порядковый номер записи в БД.
     * @param node Принимает новую запись, которую необходимо поставить на место старой.
     * @return Если запись с таким номером в БД есть, то возвращается уже обновленая запись.
     * Если такого номера нет, возвращается null.
     */
    public Node updateNode(Long id, Node node) {
       Node updateNode = getNodeById(id);
       if (updateNode != null) {
           updateNode.setTitle(node.getTitle());
           updateNode.setContent(node.getContent());
           updateNode.setDateCreation(node.getDateCreation());
           return repository.save(updateNode);
       }
       return null;
    }

    /**
     * Метод удаляет запись из базы данных.
     * @param id Принимает порядковый номер записи в БД.
     */
    public void deleteNodeById(Long id) {
        repository.deleteById(id);
    }
}
