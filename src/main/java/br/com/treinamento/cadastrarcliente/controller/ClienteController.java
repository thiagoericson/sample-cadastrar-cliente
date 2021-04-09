package br.com.treinamento.cadastrarcliente.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinamento.cadastrarcliente.model.Cliente;
import br.com.treinamento.cadastrarcliente.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
    @Autowired
    ClienteService clienteService;

    @GetMapping("")
    public List<Cliente> list() {
        return clienteService.listAllCliente();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Integer id) {
        try {
            Cliente cliente = clienteService.getCliente(id);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public void add(@Valid @RequestBody Cliente cliente) {
    	clienteService.saveCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, @PathVariable Integer id) {
        try {
            // Cliente clienteExistente = clienteService.getCliente(id);
            cliente.setId(id);
            clienteService.saveCliente(cliente);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
    }
    
    // Para o @Valid
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}