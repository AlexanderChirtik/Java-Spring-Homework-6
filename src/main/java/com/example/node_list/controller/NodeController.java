package com.example.node_list.controller;

import com.example.node_list.model.Node;
import com.example.node_list.service.NodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс взаимодействия с пользователями сервиса. Связь осуществляется по запросу http://localhost:8080/nodes
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/nodes")
public class NodeController {

    /**
     * Объект класса NodeService для использования методов сервиса.
     * Создается автоматически благодаря аннотации @RequiredArgsConstructor.
     */
    private final NodeService service;

    /**
     * Метод добавления записи в базу данных. Срабатывает при POST запросе http://localhost:8080/nodes.
     * @param node Принимает новую запись.
     * @return Возвращает список всех записей БД для демострации добавления новой записи,
     * обернутый в класс ResponseEntity, который представляет http-ответ.
     */
    @PostMapping
    public ResponseEntity<List<Node>> addNode(@RequestBody Node node) {
        service.addNode(node);
        return new ResponseEntity<>(service.getAllNode(), HttpStatus.OK);
    }

    /**
     * Метод демонстрации всех записей в базе данных. Срабатывает при GET запросе http://localhost:8080/nodes.
     * @return Возвращает список всех записей, обернутый в класс ResponseEntity, который представляет http-ответ.
     */
    @GetMapping
    public ResponseEntity<List<Node>> findAllNodes() {
        return new ResponseEntity<>(service.getAllNode(), HttpStatus.OK);
    }

    /**
     * Метод поиска записи по её порядковому номеру в БД.
     * Срабатывает при GET запросе http://localhost:8080/nodes/ + номер записи.
     * @param id Принимает номер записи.
     * @return Благодаря классу-обертке ResponseEntity нам возвращается либо запись из БД со статусом ответа 200,
     * либо пустая запись с кодом ошибки 400.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Node> getNodeById(@PathVariable Long id) {
        Node nodeById;
        try {
            nodeById = service.getNodeById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Node());
        }
        return new ResponseEntity<>(nodeById, HttpStatus.OK);
    }

    /**
     * Метод изменяет данные записи по её номеру.
     * Срабатывает при PUT запросе http://localhost:8080/nodes/ + номер записи.
     * @param id Принимает номер записи.
     * @param node Принимает новую запись.
     * @return Благодаря классу ResponseEntity нам возвращается либо обновленная запись со статусом ответа 200,
     * либо код ошибки 400.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Node> updateNode(@PathVariable Long id, @RequestBody Node node) {
        Node newNode;
        try {
            newNode = service.updateNode(id, node);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newNode, HttpStatus.OK);
    }

    /**
     * Метод удаляет запись из базы данных.
     * Срабатывает при DELETE запросе http://localhost:8080/nodes/ + номер записи.
     * @param id Принимает номер записи.
     * @return Возвращает текстовый ответ об успешности удаления либо со статусом 200, либо со статусом 400.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNode(@PathVariable Long id) {
        try {
            service.deleteNodeById(id);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("There is no node with number " + id, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Node deleted", HttpStatus.OK);
    }
}
