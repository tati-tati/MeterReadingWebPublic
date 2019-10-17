package com.tatitati.meterreading.controller;

import com.tatitati.meterreading.model.Meter;
import com.tatitati.meterreading.model.MeterReading;
import com.tatitati.meterreading.model.mService;
import com.tatitati.meterreading.model.User;
import com.tatitati.meterreading.service.MeterService;
import com.tatitati.meterreading.service.ServiceService;
import com.tatitati.meterreading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Set;

@Controller
@EnableWebMvc
public class WebController {

    @Autowired
    private UserService userService;
    @Autowired
    private MeterService meterService;
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = "/api/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findById(id));
    }

    @RequestMapping(value = "/api/users/", params = "name", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByUsername(@RequestParam(value="name") String username) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findUserByUsername(username));
    }

    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null ||
                user.getUsername() == null ||
                user.getPassword() == null) {
            return ResponseEntity.badRequest().body(user);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @RequestMapping(value = "/api/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.accepted().body(userService.save(user));
    }

    @RequestMapping(value = "/api/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(value = "/api/{id}/services", method = RequestMethod.GET)
    public ResponseEntity<Set<mService>> getUserServices(@PathVariable Integer id){
        return ResponseEntity.ok().body(userService.findById(id).getUserServices());
    }

    @RequestMapping(value = "/api/{id}/meters", method = RequestMethod.GET)
    public ResponseEntity<Set<Meter>> getUserMeters(@PathVariable Integer id){
        return ResponseEntity.ok().body(userService.findById(id).getMeters());
    }

    @RequestMapping(value = "api/{id}/meters/create", method = RequestMethod.PUT)
    public ResponseEntity<User> createMeter(@PathVariable Integer id, @RequestBody Meter meter){
        return ResponseEntity.ok().body(userService.addMeter(id, meter));
    }

    @RequestMapping(value = "api/meters/{id}/createReading", method = RequestMethod.PUT)
    public ResponseEntity<Meter> createMeterReading(@PathVariable Integer id, @RequestBody MeterReading meterReading){
        return ResponseEntity.ok().body(meterService.createMeterReading(id, meterReading));
    }

    @RequestMapping(value = "api/{id}/services/create", method = RequestMethod.PUT)
    public ResponseEntity<mService> createService(@PathVariable Integer id, @RequestBody mService service){
        return ResponseEntity.ok().body(serviceService.createService(id, service));
    }
}
