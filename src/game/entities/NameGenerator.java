package game.entities;

import java.util.Random;

public class NameGenerator {

    public static String names[] = {"Fabian", "Jose", "Jessica", "Tomas", "Beto", "Chabela", "Che", "Adolf", "Belisario",
    "Lionel", "Cristiano", "Queta", "Queto", "Chabelo", "Shirley", "Quincho", "Anastasia", "Zeus", "Loki", "Tencha", "Rodolfo",
    "Jesus", "Emeterio", "Juan", "Pedro", "Pablo", "Maria", "Kevin", "Sole", "Elsa", "Daniela", "Celeste", "Sofia", "Odin", "Destroyer",
    "Judas", "Abelardo", "Freddie", "Felipe", "Jeff", "Isaac", "Boris", "Lil", "Mario", "Luigi", "Cartman", "Kenny", "Kyle", "Stan",
    "Homero", "Bart", "Lisa", "Osama", "Fabricio", "Oscar", "Josue", "Leonardo", "Enrique", "Charlie", "Allan", "Jake", "Rick", "Morty",
    "Manuel", "Manuela", "Medusa", "Thor", "Luis", "Randall", "Hugh", "Greg", "Sheldon", "Penny", "Cara", "Emily", "Can", "Ozuna", "Chayanne",
    "Cristobal", "Raquel", "Axel", "Abraham", "Neymar", "Edna", "Optimus", "Norman", "Legolas", "Heinsemberg", "Groot", "Walker", "Bruce",
    "Bane", "Woody", "Buzz", "Bob", "Patricio", "Roy", "Donnie", "Harry", "Han", "Katniss", "Jack", "Hades", "Kratos", "Vito", "Obi wan", "Luke",
    "Yoda", "Rocky", "Darth", "James", "Kylian", "Paul", "Antoine", "Steve", "Donald", "Mauricio", "Eduardo", "Cecilia", "Cleopatra", "Paula",
    "Marta", "Pepe", "Tito"};

    public static String lastNames [] = {"Ramirez", "Acuña", "Espinoza", "Segura", "Sanchez", "Messi", "Ronaldo", "Maradona", "Pinkman", "Escobar",
    "Gaviria", "Hitler", "Alvarado", "Lopez", "420", "Pump", "Corella", "Pump", "Everdeen", "Colon", "Hitler", "Stalin", "Solo", "Vargas",
    "Villaplana", "Lightyear", "Simpson", "Bin laden", "Trump", "Roses", "Mercury", "Jovi", "Quiles", "Bunny", "Lincol", "Obama", "Harper",
    "Skywalker", "Balboa", "Rodriguez", "Vidal", "Kirby", "Levy", "Walker", "Cuervo", "Cacique", "DragonSlayer", "Jhonson", "McDonalds",
    "Kentucky", "Junior", "Joker", "Baby", "Hazard", "Pogba", "Grey", "McQueen", "Bambino", "Delevinge", "Dadario", "Maradona", "Disney",
    "Pig", "Vader", "Moda", "Increible", "Balvin", "Colindres", "Campbell", "Navas", "Guardiola", "Jordan", "Cordero", "Prime", "Bora bora",
    "Rodriguez", "Lonnis", "Vega", "Esponja", "Estrella", "Khalifa", "Balotelli", "Guevara", "De los Angeles"};

    public static String generateName(){
        String name = names[new Random().nextInt(names.length)];
        String lastName = lastNames [new Random().nextInt(lastNames.length)];
        return name + " " + lastName;
    }

}
