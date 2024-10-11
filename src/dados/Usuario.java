package dados;
public abstract class Usuario {

    private String id;
    private String nome;
    private String iniciais;

    public Usuario(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.iniciais = gerarIniciais();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIniciais() {
        return iniciais;
    }

    private String gerarIniciais() {
        String[] partes = nome.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String parte : partes) {
            sb.append(parte.charAt(0));
        }
        return sb.toString().toUpperCase();
    }
}
