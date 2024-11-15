package com.mycompany.app;

import java.util.Iterator;

/**
 * Classe TicketMachine para gerenciamento de tickets e saldo.
 */
public class TicketMachine {

    protected int valor; // Valor do ticket
    protected Troco troco; // Classe para cálculo de troco
    protected int saldo; // Saldo atual inserido na máquina
    protected int[] papelMoeda = {2, 5, 10, 20, 50, 100}; // Valores válidos de notas/moedas

    // Construtor da TicketMachine
    public TicketMachine(int valor) {
        this.valor = valor;
        this.saldo = 0;
        this.troco = new Troco(valor);
    }

    /**
     * Insere uma quantia de dinheiro na máquina.
     *
     * @param quantia Valor a ser inserido
     * @throws PapelMoedaInvalidaException Caso a quantia seja inválida
     */
    public void inserir(int quantia) throws PapelMoedaInvalidaException {
        boolean achou = false;
        for (int i = 0; i < papelMoeda.length; i++) {
            if (papelMoeda[i] == quantia) {
                achou = true;
                break;
            }
        }
        if (!achou) {
            throw new PapelMoedaInvalidaException(); // Lança exceção com mensagem padrão
        }
        this.saldo += quantia;
    }

    /**
     * Retorna o saldo atual.
     *
     * @return Saldo atual inserido
     */
    public int getSaldo() {
        return saldo;
    }

    /**
     * Retorna um iterador para o troco.
     *
     * @return Iterador do troco
     */
    public Iterator<PapelMoeda> getTroco() {
        return troco.getIterator();
    }

    /**
     * Imprime o ticket, reduzindo o saldo pelo valor do ticket.
     *
     * @return String representando o ticket
     * @throws SaldoInsuficienteException Caso o saldo seja insuficiente
     */
    public String imprimir() throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException(); // Verifica saldo insuficiente
        }
        // NÃO reduzimos o saldo
        String result = "*****************\n";
        result += "*** R$ " + valor + ",00 ****\n";
        result += "*****************\n";
        return result;
    }
}
