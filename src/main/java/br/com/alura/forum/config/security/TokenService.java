package br.com.alura.forum.config.security;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal(); //Esse authentication tem um método chamado getPrincipal para conseguirmos recuperar o usuário que está logado
        Date hoje = new Date();
        Date dataExpiration = new Date(hoje.getTime() + Long.parseLong(expiration)); //Eu estou somando os milisegundo da data hoje com e acrescentando mais os milisegundos da expiration;
        return Jwts.builder()
                .setIssuer("Api do Fórum da Alura") //Quem está fazendo a requisição;
                .setSubject(logado.getId().toString())//quem é o dono desse token
                .setIssuedAt(hoje)
                .setExpiration(dataExpiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}
