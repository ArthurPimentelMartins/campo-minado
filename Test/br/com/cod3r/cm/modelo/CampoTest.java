package br.com.cod3r.cm.modelo;

import br.com.cod3r.cm.excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {

    private Campo campo;

    @BeforeEach
    void iniciarCampo() {
        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoRealDistancia1() {

        Campo vizinhoEsquerda = new Campo(3, 2);
        boolean resultadoEsquerda = campo.adicionarVizinho(vizinhoEsquerda);
        assertTrue(resultadoEsquerda);

        Campo vizinhoDireita = new Campo(3, 4);
        boolean resultadoDireita = campo.adicionarVizinho(vizinhoDireita);
        assertTrue(resultadoDireita);

        Campo vizinhoCima = new Campo(2, 3);
        boolean resultadoCima = campo.adicionarVizinho(vizinhoCima);
        assertTrue(resultadoCima);

        Campo vizinhoBaixo = new Campo(4, 3);
        boolean resultadoBaixo = campo.adicionarVizinho(vizinhoBaixo);
        assertTrue(resultadoBaixo);
    }

    @Test
    void testeVizinhoRealDistancia2Diagonal() {
        Campo vizinho = new Campo(2, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinhoRealDistancia2Diagonal() {
        Campo vizinho = new Campo(1, 2);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();

        assertThrows(ExplosaoException.class, () -> {
            campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1() {

        Campo campo11 = new Campo(1, 1);

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);
        campo.abrir();
        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {

        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 1);
        campo12.minar();

        Campo campo22 = new Campo(2, 2);
        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);
        campo.abrir();

        assertFalse(campo12.isAberto() && !campo11.isAberto());
    }
}