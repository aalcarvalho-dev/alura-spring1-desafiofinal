package com.alura.spring_desafiofinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.alura.spring_desafiofinal.model.Dados;
import com.alura.spring_desafiofinal.model.DadosModelo;
import com.alura.spring_desafiofinal.model.DadosVeiculo;
import com.alura.spring_desafiofinal.model.Veiculo;
import com.alura.spring_desafiofinal.service.ConsumoAPI;
import com.alura.spring_desafiofinal.service.ConverteDados;

public class Principal {

    String tipoVeiculo;
    String marcaVeiculo;
    String modeloVeiculo;
    String codModeloVeiculo;
    private static final String API = "https://parallelum.com.br/fipe/api/v1/";
    private static final String API_MARCA = "/marcas/";
    private static final String API_MODELOS = "/modelos/";
    private static final String API_ANOS = "/anos/";

    ConsumoAPI api = new ConsumoAPI();
    ConverteDados conversor = new ConverteDados();
    List<Dados> listaDados = new ArrayList<>();
    DadosModelo modelos;
    //Dados dados;

    public void inicia(){
        System.out.println("Bem-vindos ao sistema de consulta FIPE");
        menu();

    }

    public void menu(){
        promptVeiculo();
    }
    
    private void promptVeiculo() {
        Scanner leitura = new Scanner(System.in);
        System.out.print("\nDigite o tipo de veículo (carros/motos/caminhoes) ou 0 pra sair: ");
        tipoVeiculo = leitura.nextLine();
        System.out.println("Você digitou: "+tipoVeiculo);
        if(tipoVeiculo.equalsIgnoreCase("0")){
            sair();
            return;
        }
        if (!tipoVeiculo.equalsIgnoreCase("carros") && !tipoVeiculo.equalsIgnoreCase("motos") && !tipoVeiculo.equalsIgnoreCase("caminhoes")) {
            System.out.println("Opção inválida");
            menu();
        }
        buscarVeiculo();
        promptMarca();
    }
    
    private void promptMarca() {
        Scanner leitura = new Scanner(System.in);
        System.out.print("\nInforme o código da marca de ("+ tipoVeiculo +") para consulta ou 0 para voltar ao menu anterior: ");
        marcaVeiculo = leitura.nextLine();
        System.out.println("Você digitou: "+marcaVeiculo);
        if (marcaVeiculo.equalsIgnoreCase("0")) {
            menu();
        }
        buscarMarca();
    }

    private void promptModelo() {
        Scanner leitura = new Scanner(System.in);
        System.out.print("\nDigite um trecho do nome do modelo do veículo ou 0 para voltar ao menu anterior: ");
        modeloVeiculo = leitura.nextLine();
        System.out.println("Você digitou: "+modeloVeiculo);
        if (modeloVeiculo.equalsIgnoreCase("0")) {
            promptMarca();
        }
        filtraPorModelo();
        //buscarModelo();
    }

    private void promptCodModelo() {
        Scanner leitura = new Scanner(System.in);
        System.out.print("\nDigite o código do modelo do veículo ou 0 para voltar ao menu anterior: ");
        codModeloVeiculo = leitura.nextLine();
        System.out.println("Você digitou: "+codModeloVeiculo);
        if (codModeloVeiculo.equalsIgnoreCase("0")) {
            promptMarca();
        }
        buscarModelo();
    }

    private void buscarVeiculo() {
        var retorno = api.obterDados(API+tipoVeiculo+API_MARCA);
        listaDados = conversor.obterListaDados(retorno, Dados.class);
        listaDados.forEach(d -> {
            System.out.println("COD: "+d.codigo()+"   DESCRIÇÃO: "+d.nome());
        });

    }
    
    private void buscarMarca() {
        String retorno = api.obterDados(API+tipoVeiculo+API_MARCA+marcaVeiculo+API_MODELOS);
        modelos = conversor.obterDados(retorno, DadosModelo.class);
        modelos.modelos().forEach(d -> {
            System.out.println("COD: "+d.codigo()+"   DESCRIÇÃO: "+d.nome());
        });
        promptModelo();
    }

    private void filtraPorModelo() {
        List<Dados> modelosFiltrados = modelos.modelos().stream()
                        .filter(m -> m.nome().toUpperCase().contains(modeloVeiculo.toUpperCase()))
                        .collect(Collectors.toList());
        System.out.println("Modelos Filtrados");
        modelosFiltrados.forEach(System.out::println);
        promptCodModelo();
    }

    private void buscarModelo() {
        String retorno = api.obterDados(API+tipoVeiculo+API_MARCA+marcaVeiculo+API_MODELOS+codModeloVeiculo+API_ANOS);
        listaDados = conversor.obterListaDados(retorno, Dados.class);
        listaDados.forEach(d -> {
            detalharModelo(d.codigo());
        });
    }

    private void detalharModelo(String codigo) {
        String retorno = api.obterDados(API+tipoVeiculo+API_MARCA+marcaVeiculo+API_MODELOS+codModeloVeiculo+API_ANOS+codigo);
        //System.out.println(retorno);
        DadosVeiculo dadosVeiculo = conversor.obterDados(retorno, DadosVeiculo.class);
        System.out.println(dadosVeiculo);
    }

    private void sair() {
        System.out.println("Até logo!");
        System.exit(0);
    }
}
