package com.maliburger.MALIBURGER.Service;

import com.maliburger.MALIBURGER.Model.Malibur;
import com.maliburger.MALIBURGER.Repository.MaliRepository;
import com.maliburger.MALIBURGER.dto.MaliDtoResponse;
import com.maliburger.MALIBURGER.dto.MalidtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class maliService {

    @Autowired
    private MaliRepository maliRepository;

    //convierte un objeto de la bdd a un dto
    private MaliDtoResponse mapToDTO(Malibur m){
        return new MaliDtoResponse(m.getIdBurger(), m.getPrecio(), m.getDescripcion(), m.getNombre(), m.getProteina());
    }

    //llama a todas las hamburguesas
    public List<MaliDtoResponse> getAllMaliburs(){
        return maliRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //busca por la hamburguesa según su id y la convierte a dto.
    public Optional<MaliDtoResponse> obtenerPorId(Long id){
        return maliRepository.findById(id).map(this::mapToDTO);
    }

    //busca la hamburguesa según su "proteina"
    public List<MaliDtoResponse> buscarPorProteina(String proteina){
        return maliRepository.buscarPorProteina(proteina).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //Busca hamburguesas según su precio.
    public List<MaliDtoResponse> buscarPorPrecio(int min, int max){
        return maliRepository.findByprecioBetween(min, max).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //convierte la "orden" del cliente en un objeto para que la bdd lo entienda
    public MaliDtoResponse guardarMali(MalidtoRequest dto){
        Malibur m = new Malibur(null, dto.getPrecio(), dto.getDescripcion(), dto.getNombre(), dto.getProteina());
        return mapToDTO(maliRepository.save(m));
    }

    //busca una hamburguesa que exista, cambia sus datos y los guarda nuevamente
    public Optional<MaliDtoResponse> actualizar(long id, MalidtoRequest dto){
        return maliRepository.findById(id).map(existente -> {
            existente.setNombre(dto.getNombre());
            existente.setDescripcion(dto.getDescripcion());
            existente.setPrecio(dto.getPrecio());
            existente.setProteina(dto.getProteina());
            return mapToDTO(maliRepository.save(existente));
        });
    }

    //borra algo en la bdd
    public void eliminar(Long id){
        maliRepository.deleteById(id);
    }
}