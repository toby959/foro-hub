package com.toby959.foro_hub.repository;

import com.toby959.foro_hub.models.Status;
import com.toby959.foro_hub.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByTitleAndMessage(String title, String message);

    boolean existsByTitleAndMessageAndIdNot(String title, String message, Long id);

    Page<Topic> findByOrderByCreationDateDesc(Pageable pageable);

    Page<Topic> findTop10ByStatusOrderByCreationDateDesc(Status status, Pageable pageable);
}
