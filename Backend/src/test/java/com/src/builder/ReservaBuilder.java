package com.src.builder;

import com.src.dominio.Cliente;
import com.src.dominio.Reserva;
import com.src.dominio.Sala;
import com.src.dominio.TipoSala;
import com.src.repositorio.ReservaRepositorio;
import com.src.servico.ReservaServico;
import com.src.servico.dto.ReservaDTO;
import com.src.servico.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
//paulo.teotonio
@Component
public class ReservaBuilder extends ConstrutorDeEntidade<Reserva>{

    @Autowired
    private ReservaServico reservaServico;

    @Autowired
    private ReservaMapper reservaMapper;
    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Autowired
    private ClienteBuilder clienteBuilder;
    @Autowired
    private SalaBuilder salaBuilder;

    @Override
    public Reserva construirEntidade() throws ParseException {

        Reserva reserva = new Reserva();
        reserva.setDataFim(LocalDate.now().plusDays(1));
        reserva.setDataIni(LocalDate.now().plusDays(90));
        reserva.setTotal(455.00);

        Cliente cliente = clienteBuilder.construir();
        Sala sala = salaBuilder.construir();
        reserva.setCliente(cliente);
        reserva.setSala(sala);

        return reserva;
    }

    @Override
    public Reserva persistir(Reserva entidade){
        ReservaDTO dto = reservaMapper.toDto(entidade);
        return  reservaMapper.toEntity(reservaServico.inserir(dto));
    }

    @Override
    public Collection<Reserva> obterTodos()
    {
        return reservaRepositorio.findAll();
    }

    @Override
    public Reserva obterPorId(Integer id)
    {
        return reservaMapper.toEntity(reservaServico.buscar(id));
    }

    public void deletarPorId(Integer id){
        reservaRepositorio.deleteById(id);
    }

    public ReservaDTO converterToDto(Reserva reserva){
        return reservaMapper.toDto(reserva);
    }

}