package com.example.node_list.repository;

import com.example.node_list.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Интерфейс для подключения к Spring Data с помощью интерфейса JpaRepository. В него передаем класс-сущность Node
 * и тип данных первичного ключа в таблице.
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
}
