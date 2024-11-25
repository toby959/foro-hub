package com.toby959.foro_hub.service;

import com.toby959.foro_hub.dto.TopicDataCreate;
import com.toby959.foro_hub.dto.TopicDataUpdate;
import com.toby959.foro_hub.error.TopicValidationException;
import com.toby959.foro_hub.models.Status;
import com.toby959.foro_hub.models.Topic;
import com.toby959.foro_hub.repository.ICourseRepository;
import com.toby959.foro_hub.repository.ITopicRepository;
import com.toby959.foro_hub.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final ICourseRepository courseRepository;

    private final ITopicRepository topicRepository;

    private final IUserRepository userRepository;

    public Topic create(TopicDataCreate data) {
        if(!userRepository.existsById(data.user())){
            throw new TopicValidationException("autor","No existe un usuario con el identificador " + data.user());
        }

        if(!courseRepository.existsById(data.course())){
            throw new TopicValidationException("curso","No existe un curso con el identificador " + data.course());
        }

        if(topicRepository.existsByTitleAndMessage(data.title(), data.message())) {
            throw new TopicValidationException("topico","Ya existe un tópico con igual título y mensaje");
        }

        var user = userRepository.getReferenceById(data.user());
        var course = courseRepository.getReferenceById(data.course());
        var topic = new Topic(data.title(), data.message(), user, course);

        topicRepository.save(topic);

        return topic;
    }
//###################

public Topic read(Long id){
    if(!topicRepository.existsById(id)){
        throw new TopicValidationException("id","No existe un tópico con el identificador " + id);
    }
    return topicRepository.getReferenceById(id);
}


//###################
    public Page<Topic> findAll(Pageable pageable) {
        return topicRepository.findByOrderByCreationDateDesc(pageable);
    }
//###################
    public Page<Topic> findTop10New(Status status, Pageable pageable) {
        return topicRepository.findTop10ByStatusOrderByCreationDateDesc(status, pageable);
    }
//###################
public Topic update(TopicDataUpdate data) {
    // Verifica si existe el tópico
    var topicOptional = topicRepository.findById(data.id());
    if (!topicOptional.isPresent()) {
        throw new TopicValidationException("topico", "No existe un tópico con el identificador " + data.id());
    }

    // Obtén el tópico
    var topic = topicOptional.get();

    // Accede a las propiedades sin problemas
    String title = topic.getTitle();
    String message = topic.getMessage();
    Status status = topic.getStatus();

    // Compara los valores
    var equalTitle = title != null && title.equals(data.title());
    var equalMessage = message != null && message.equals(data.message());
    var equalStatus = status == data.status();

    if (equalTitle && equalMessage && equalStatus) {
        throw new TopicValidationException("topico", "Los datos suministrados son iguales a los existentes");
    }

    if (!equalTitle || !equalMessage) {
        if (topicRepository.existsByTitleAndMessageAndIdNot(data.title(), data.message(), data.id())) {
            throw new TopicValidationException("topico", "Ya existe un tópico con igual título y mensaje");
        }
    }

    // Actualiza el tópico
    topic.update(data);
    topicRepository.save(topic);

    return topic;
}
//###################
    public void delete(Long id) {
        if(!topicRepository.existsById(id)){
            throw new TopicValidationException("id","No existe un tópico con el identificador " + id);
        }
        topicRepository.deleteById(id);
    }


}

