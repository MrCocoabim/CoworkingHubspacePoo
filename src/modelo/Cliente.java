package modelo;

public class Cliente {
    private String cpf;
    private String nome;
    private String email;

    public Cliente(String cpf, String nome, String email) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        this.cpf = cpf.trim();
        this.nome = nome.trim();
        this.email = email.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + " | " + email + ")";
    }
}