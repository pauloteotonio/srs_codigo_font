package com.basis.src.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Getter
@Setter
@Table(name ="equipamento")
public class Equipamento implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_equipamento")
    @SequenceGenerator(name="sq_equipamento", allocationSize = 1, sequenceName = "sq_equipamento")
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="id_tipo_equipamento")
    private TipoEquipamento tipoEquipamento;

    @Column(name ="nome")
    private String nome;

    @Column(name ="preco_diario")
    private Double preco;

    @Column(name ="obrigatorio")
    private Integer obrigatorio;

}