package com.toby959.foro_hub.controller;

import com.toby959.foro_hub.dto.TopicDataCreate;
import com.toby959.foro_hub.dto.TopicDataUpdate;
import com.toby959.foro_hub.dto.TopicDataView;
import com.toby959.foro_hub.models.Status;
import com.toby959.foro_hub.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;


    @PostMapping
    @Transactional
    public ResponseEntity<TopicDataView> create(
            @RequestBody @Valid TopicDataCreate dataCreate,
            UriComponentsBuilder uriComponentsBuilder) {
        var dataView = new TopicDataView(topicService.create(dataCreate));
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(dataView.id()).toUri();
        return ResponseEntity.created(url).body(dataView);
    }
//###############
    @GetMapping("/{id}")
    public ResponseEntity<TopicDataView> read(@PathVariable() Long id) {
        var dataView = new TopicDataView(topicService.read(id));
        return ResponseEntity.ok(dataView);
    }
//###############
    @GetMapping
    public ResponseEntity<Page<TopicDataView>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        var dataList = topicService.findAll(pageable).map(TopicDataView::new);
        return ResponseEntity.ok(dataList);
    }
//###############
    @GetMapping("/top10")
    public ResponseEntity<Page<TopicDataView>> findTop10New(@PageableDefault(size = 10) Pageable pageable) {
        var dataList = topicService.findTop10New(Status.ACTIVE,pageable).map(TopicDataView::new);
        return ResponseEntity.ok(dataList);
    }
//###############

    @PutMapping
    @Transactional
    public ResponseEntity<TopicDataView> update(
            @RequestBody @Valid TopicDataUpdate dataUpdate,
            UriComponentsBuilder uriComponentsBuilder) {
        var dataView = new TopicDataView(topicService.update(dataUpdate));
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(dataView.id()).toUri();
        return ResponseEntity.ok().location(url).body(dataView);
    }
//###############
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicDataView> delete(@PathVariable() Long id) {
        topicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
