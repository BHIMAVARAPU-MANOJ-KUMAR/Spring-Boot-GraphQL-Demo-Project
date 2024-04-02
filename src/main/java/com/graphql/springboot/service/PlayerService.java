package com.graphql.springboot.service;

import com.graphql.springboot.model.Player;
import com.graphql.springboot.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {
    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll(){
        return players;
    }

    public Optional<Player> findOne(Integer id) {
        return players.stream().filter(player -> player.Id() == id).findFirst();
    }

    public Player createPlayer(String name, Team team){
        Player player = new Player(id.incrementAndGet(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(Integer id) {
        Player player = players.stream().filter(c -> c.Id() == id)
                .findFirst().orElseThrow(()->new IllegalArgumentException());
        players.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team) {
        Player updatePlayer = new Player(id, name, team);
        Optional<Player> optional = players.stream().filter(c -> c.Id() == id).findFirst();
        if (optional.isPresent()){
            Player player = optional.get();
            int index = players.indexOf(player);
            players.set(index, updatePlayer);
        }
        else {
            throw new IllegalArgumentException("Invalid Player.");
        }
        return updatePlayer;
    }

    @PostConstruct
    private void init(){
        players.add(new Player(id.incrementAndGet(), "M.S. Dhoni", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Jasprit Bumrah", Team.MI));
        players.add(new Player(id.incrementAndGet(), "Suresh Raina", Team.CSK));
        players.add(new Player(id.incrementAndGet(), "Virat Kohli", Team.RCB));
        players.add(new Player(id.incrementAndGet(), "Glenn Maxwell", Team.RCB));
        players.add(new Player(id.incrementAndGet(), "Travis Head",Team.SRH));
        players.add(new Player(id.incrementAndGet(), "H Klaasen", Team.SRH));
        players.add(new Player(id.incrementAndGet(), "Shreyas Iyer", Team.KKR));
        players.add(new Player(id.incrementAndGet(), "Sanju Samson", Team.RR));
        players.add(new Player(id.incrementAndGet(), "Jos Buttler", Team.RR));
    }
}
