package com.mycompany.app;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void TesteGetQuantidade(){

        PapelMoeda papel = new PapelMoeda(10, 5);

        int quantidade = papel.getQuantidade();

        Assert.assertEquals(5, quantidade);
    }

    @Test
    public void testCriarPapelMoedaValido() {
        PapelMoeda nota = new PapelMoeda(50, 2); 
        
        Assert.assertEquals(50, nota.getValor()); 
        Assert.assertEquals(2, nota.getQuantidade());
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void testeImprimirSaldoInsuficiente() throws SaldoInsuficienteException, PapelMoedaInvalidaException {
        TicketMachine machine = new TicketMachine(20);
        machine.inserir(10);
        machine.imprimir();
    }

    @Test
    public void testEmitirTicketComSaldoSuficiente() throws SaldoInsuficienteException, PapelMoedaInvalidaException {
        TicketMachine machine = new TicketMachine(5);
        machine.inserir(10);
        machine.imprimir();
        Assert.assertEquals(10, machine.getSaldo()); // O saldo deve ser atualizado após emitir o ticket
    }

    @Test(expected = PapelMoedaInvalidaException.class)
    public void testInserirDinheiroMenorQueZero() throws PapelMoedaInvalidaException {
        TicketMachine machine = new TicketMachine(5);
        machine.inserir(-5); // Valor inválido
    }

}
