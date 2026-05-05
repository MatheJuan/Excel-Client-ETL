package Model;

public class Cliente {

    private Long id;
    private String nome;
    private long id_login;
    private boolean login_ativo;
    private boolean login_online;
    private String mac;

    public Cliente() {
    }

    public Cliente(Long id, String nome, long id_login, boolean login_ativo, boolean login_online, String mac) {
        this.id = id;
        this.nome = nome;
        this.id_login = id_login;
        this.login_ativo = login_ativo;
        this.login_online = login_online;
        this.mac = mac;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId_login() {
        return id_login;
    }

    public void setId_login(long id_login) {
        this.id_login = id_login;
    }

    public boolean isLogin_ativo() {
        return login_ativo;
    }

    public void setLogin_ativo(boolean login_ativo) {
        this.login_ativo = login_ativo;
    }

    public boolean isLogin_online() {
        return login_online;
    }

    public void setLogin_online(boolean login_online) {
        this.login_online = login_online;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}


