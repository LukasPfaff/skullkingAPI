package lukaspfaff.skullkingAPI.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public class GetLocationHelper {
    protected static URI getLocation(UUID id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
    }
}
