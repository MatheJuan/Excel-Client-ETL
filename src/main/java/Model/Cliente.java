package Model;

public class Cliente {

    private Long id;
    private String nome;
    private String mac;

    public Cliente(){

    }
    public Cliente(String mac, String nome, Long id) {
        this.mac = mac;
        this.nome = nome;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMac() {
        return mac;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
