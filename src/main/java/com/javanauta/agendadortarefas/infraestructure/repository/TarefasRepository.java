package com.javanauta.agendadortarefas.infraestructure.repository;

import com.javanauta.agendadortarefas.infraestructure.entity.TarefasEntity;
import com.javanauta.agendadortarefas.infraestructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(LocalDateTime dataInicial,
                                                                        LocalDateTime dataFinal,
                                                                        StatusNotificacaoEnum statusNotificacaoEnum);

    List<TarefasEntity> findByEmailUsuario(String email);
}
