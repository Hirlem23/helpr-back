package com.api.helprback.resources;

import com.api.helprback.domain.Chamado;
import com.api.helprback.domain.dtos.ChamadoDTO;
import com.api.helprback.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/service/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = this.chamadoService.findById(id);
        return  ResponseEntity.ok().body(new ChamadoDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = chamadoService.findAll();
        List<ChamadoDTO> listDto = list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public  ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDto) {
        Chamado obj = chamadoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
