package com.mycompany.app;

import org.junit.Assert;
import org.junit.Test;
import java.util.Iterator;

/**
 * Unit test for simple App.
 */
public class TicketMachineTest 
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

    @Test
    public void testInserirMultiplosValoresValidos() throws PapelMoedaInvalidaException {
        TicketMachine machine = new TicketMachine(50);
        machine.inserir(20);
        machine.inserir(20);
        machine.inserir(10);
        Assert.assertEquals(50, machine.getSaldo()); // Deve somar 20 + 20 + 10
    }

    @Test(expected = PapelMoedaInvalidaException.class)
    public void testInserirNotaInvalida() throws PapelMoedaInvalidaException {
        TicketMachine machine = new TicketMachine(20);
        machine.inserir(3); // Valor não está no array de papelMoeda
    }


    @Test(expected = SaldoInsuficienteException.class)
    public void testImprimirComSaldoInsuficiente() throws SaldoInsuficienteException, PapelMoedaInvalidaException {
        TicketMachine machine = new TicketMachine(50);
        machine.inserir(10); // Saldo insuficiente
        machine.imprimir();
    }

    @Test
    public void testNaoAumentarSaldoComNotaInvalida() {
        TicketMachine machine = new TicketMachine(10);
        try {
            machine.inserir(15); // Não é uma nota válida
        } catch (PapelMoedaInvalidaException e) {
            // Exceção esperada
        }
        Assert.assertEquals(0, machine.getSaldo()); // Saldo deve continuar 0
    }

    @Test
    public void testTrocoComValorExato() {
        Troco troco = new Troco(186);  // Valor com múltiplas notas
        Iterator<PapelMoeda> iterator = troco.getIterator();
        Assert.assertTrue(iterator.hasNext());  // Verifica se existe troco
    }

    @Test
    public void testTrocoComNotasPequenas() {
        Troco troco = new Troco(7);  // Notas de 5 e 2
        Iterator<PapelMoeda> iterator = troco.getIterator();
        Assert.assertTrue(iterator.hasNext());  // Deve haver troco
    }
}
