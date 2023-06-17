package com.pastor126.modelo;


public class Moto {
		
		private Long id;
		private String marca;	
		private String modelo; 	
		private String cor;
		
		public Moto() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
		public Moto(Long id, String marca, String modelo, String cor) {
			super();
			this.id = id;
			this.marca = marca;
			this.modelo = modelo;
			this.cor = cor;
		}



		public Moto(String marca, String modelo,  String cor) {
			super();
			this.marca = marca;
			this.modelo = modelo;
			this.cor = cor;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		public String getCor() {
			return cor;
		}
		public void setCor(String cor) {
			this.cor = cor;
		}
		
		
		
		
}
