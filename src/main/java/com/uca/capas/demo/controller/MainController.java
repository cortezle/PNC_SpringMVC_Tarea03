package com.uca.capas.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping("/ingresar")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/resultado")
	public ModelAndView user1(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		List<String> listaErrores = new ArrayList<>();
		
		validarNombre( nombre, listaErrores, mav);
		validarApellido(apellido,listaErrores,mav);
		
		
		if(!listaErrores.isEmpty()) {
			mav.addObject("errores", listaErrores);
			mav.addObject("apellido", apellido);
			mav.setViewName("registro_error.html");
		}else {
			mav.addObject("apellido", "exito");
			mav.setViewName("registrado.html");
		}

		return mav;
	}
	
	public Boolean validarMayorCero(String nom) {
		if(nom.length()>0) {
			return true;
		}
		else {
			return false;
		}
	}
	public Boolean validarMayor25(String nom) {
		if(nom.length()>25) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void validarNombre(String nombre, List<String> listaErrores, ModelAndView mav) {
		if(validarMayorCero(nombre)) {
			if(validarMayor25(nombre)) {
				listaErrores.add("El nombre fue mayor a 25");
			}
			else {
				mav.addObject("nombre", nombre);
			}
			
		}else {
			listaErrores.add("El nombre esta vacio");
		}
		
	}
	public void validarApellido(String apellido, List<String> listaErrores, ModelAndView mav) {
		if(validarMayorCero(apellido)) {
			if(validarMayor25(apellido)) {
				listaErrores.add("El apellido fue mayor a 25");
			}
			else {
				mav.addObject("apellido", apellido);
			}
			
		}else {
			listaErrores.add("El apellido esta vacio");
		}
	}
	

	

}