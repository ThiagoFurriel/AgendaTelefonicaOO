import java.io.*;

public class Agenda {
    private Contato[] contatos;
    private int ultimo;

    public Agenda() {
        contatos = new Contato[1000];
        ultimo = 0;
    }

    public boolean isCheia() {
        return ultimo >= contatos.length;
    }

    public int getQuantidade() {
        return ultimo;
    }

    public Contato buscar(String nome) {
        for (int i = 0; i < ultimo; i++) {
            if (contatos[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
                return contatos[i];
            }
        }
        return null;
    }

    public void inserir(Contato novo) {
        Contato existente = buscar(novo.getNome());

        if (existente != null) {
            existente.setTelefone(novo.getTelefone());
            existente.setEndereco(novo.getEndereco());
            existente.setRelacao(novo.getRelacao());
        } else {


            if (!isCheia()) {
                contatos[ultimo] = novo;
                ultimo++;
            } else {
                System.out.println("Erro: A agenda está cheia! Não é possível adicionar " + novo.getNome());
            }
        }
    }

    public void remover(String nome) {
        for (int i = 0; i < ultimo; i++) {
            if (contatos[i].getNome().equalsIgnoreCase(nome)) {

                for (int j = i; j < ultimo - 1; j++) {
                    contatos[j] = contatos[j + 1];
                }
                contatos[ultimo - 1] = null;
                ultimo--;
                return;
            }
        }
    }

    public void listar() {
        System.out.println("Total de contatos: " + getQuantidade());
        for (int i = 0; i < ultimo; i++) {
            System.out.println(contatos[i]);
        }
    }

    public void salvarArquivo(String nomeArquivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < ultimo; i++) {
                pw.println(contatos[i].getNome() + ";" +
                        contatos[i].getTelefone() + ";" +
                        contatos[i].getEndereco() + ";" +
                        contatos[i].getRelacao());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void carregarArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 4) {
                    inserir(new Contato(dados[0], dados[1], dados[2], dados[3]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}