package br.com.luiza.client.enumarations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperacaoEnum {

    CONSULTAR( "consultar"),
    CRIAR( "criar"),
    ALTERAR( "alterar"),
    APAGAR( "apagar");

    String name;
}
