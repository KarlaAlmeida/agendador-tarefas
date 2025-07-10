package com.javanauta.agendadortarefas.business;

import com.javanauta.agendadortarefas.business.dto.TarefasDTO;
import com.javanauta.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.javanauta.agendadortarefas.business.mapper.TarefasConverter;
import com.javanauta.agendadortarefas.infraestructure.entity.TarefasEntity;
import com.javanauta.agendadortarefas.infraestructure.enums.StatusNotificacaoEnum;
import com.javanauta.agendadortarefas.infraestructure.exceptions.ResourceNotFoundException;
import com.javanauta.agendadortarefas.infraestructure.repository.TarefasRepository;
import com.javanauta.agendadortarefas.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefasDTO gravarTarefa(String token, TarefasDTO tarefasDTO){
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        tarefasDTO.setEmailUsuario(email);

        TarefasEntity entity = tarefasConverter.paraTarefasEntity(tarefasDTO);
        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscaTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletarTarefaPorId(String id){
        try{
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Id inexistente" + id, e.getCause());
        }
    }

    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id){
        try {
            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar tarefa ", e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO tarefasDTO, String id){
        try {
            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefaUpdateConverter.updateTarefas(tarefasDTO, entity);
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alte" + id, e.getCause());
        }
    }

}
