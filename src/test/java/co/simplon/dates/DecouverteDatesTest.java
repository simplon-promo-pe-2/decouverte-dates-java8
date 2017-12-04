package co.simplon.dates;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;

public class DecouverteDatesTest {

	static DecouverteDates dd;
	
	@BeforeClass
	public static void init() {
		dd = new DecouverteDates();
	}
	
	@Test
	public void la_date_1er_janvier_2017_doit_etre_inferieure_a_la_date_courante() throws ParseException {
		LocalDate date1erJanvier = LocalDate.of(2017, Month.JANUARY, 1); 
	
		boolean resultatComparaison = dd.estInferieurDateCourante(date1erJanvier);
		
		assertTrue(resultatComparaison);
	}
	
	@Test
	public void la_date_3_juin_2050_doit_etre_superieure_a_la_date_courante() throws ParseException {
		LocalDate date3Juin = LocalDate.of(2050, Month.JUNE, 3);
		
		boolean resultatComparaison = dd.estInferieurDateCourante(date3Juin);
		
		assertFalse(resultatComparaison);
	}
	
	@Test
	public void la_chaine_fournie_est_convertie_en_la_bonne_date_1() throws ParseException {
		String chaineFournie = "04/04/2017";
		
		LocalDate dateConstruite = dd.construireDate(chaineFournie);
		
		assertThat(dateConstruite.getDayOfMonth(), equalTo(4));
		assertThat(dateConstruite.getMonth(), equalTo(Month.APRIL));
		assertThat(dateConstruite.getYear(), equalTo(2017));
	}
	
	@Test
	public void la_chaine_fournie_est_convertie_en_la_bonne_date_2() throws ParseException {
		String chaineFournie = "31/12/2017";
		
		LocalDate dateConstruite = dd.construireDate(chaineFournie);
		
		assertThat(dateConstruite.getDayOfMonth(), equalTo(31));
		assertThat(dateConstruite.getMonth(), equalTo(Month.DECEMBER));
		assertThat(dateConstruite.getYear(), equalTo(2017));
	}
	
	@Test
	public void la_date_est_incrementee_d_un_jour_deux_heures_trente_minutes() throws ParseException {
		LocalDateTime date3Juin = LocalDateTime.of(2017, Month.JUNE, 3, 0, 0, 0);
		
		LocalDateTime nouvelleDate = dd.augmenterDate(date3Juin, 1, 2, 30);
		
		assertThat(nouvelleDate.getDayOfMonth(), equalTo(4));
		assertThat(nouvelleDate.getMonth(), equalTo(Month.JUNE));
		assertThat(nouvelleDate.getYear(), equalTo(2017));
		assertThat(nouvelleDate.getHour(), equalTo(2));
		assertThat(nouvelleDate.getMinute(), equalTo(30));
	}
	
	@Test
	public void la_date_est_incrementee_d_un_jour_et_diminuee_de_vingt_minutes() throws ParseException {
		LocalDate date = LocalDate.of(2017, Month.JUNE, 3);
		LocalTime time = LocalTime.of(0, 10);
		LocalDateTime date3Juin = LocalDateTime.of(date, time);
		
		LocalDateTime nouvelleDate = dd.augmenterDate(date3Juin, 1, 0, -20);
		
		assertThat(nouvelleDate.getDayOfMonth(), equalTo(3));
		assertThat(nouvelleDate.getMonth(), equalTo(Month.JUNE));
		assertThat(nouvelleDate.getYear(), equalTo(2017));
		assertThat(nouvelleDate.getHour(), equalTo(23));
		assertThat(nouvelleDate.getMinute(), equalTo(50));
	}
	
	@Test
	public void le_formattage_de_la_date_est_31_01_2017() throws ParseException {
		LocalDate date = LocalDate.of(2017, Month.JANUARY, 31); 
		
		String dateFormatee = dd.formaterUneDate(date);
		
		assertThat(dateFormatee, equalTo("31_01_2017"));
	}
	
	@Test
	public void le_formatage_de_la_date_est_01_12_2017() throws ParseException {
		LocalDate date = LocalDate.of(2017, Month.DECEMBER, 1); 
		
		String dateFormatee = dd.formaterUneDate(date);
		
		assertThat(dateFormatee, equalTo("01_12_2017"));
	}
	
	@Test
	public void le_formatage_de_l_heure_est_21h30min25sec() throws ParseException {
		LocalTime heure = LocalTime.of(21, 30, 25);
		
		String heureFormatee = dd.formaterUneHeure(heure);
		
		assertThat(heureFormatee, equalTo("21h30min25sec"));
	}
	
	@Test
	public void le_formatage_de_la_date_en_francais_doit_etre_conforme() throws ParseException {
		LocalDate date = LocalDate.of(2017, Month.JANUARY, 31); 
		
		String dateFormateeFr = dd.formaterDateEnFrancais(date);
		
		assertThat(dateFormateeFr, equalTo("mardi 31 janvier 2017"));
	}
}
