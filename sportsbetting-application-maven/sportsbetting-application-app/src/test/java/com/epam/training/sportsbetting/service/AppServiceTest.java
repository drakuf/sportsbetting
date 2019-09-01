//package com.epam.training.sportsbetting.service;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.io.PrintStream;
//import java.math.BigDecimal;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.epam.training.sportsbetting.App;
//import com.epam.training.sportsbetting.domain.Currency;
//import com.epam.training.sportsbetting.domain.Player;
//import com.epam.training.sportsbetting.service.SportsBettingService;
//import com.epam.training.sportsbetting.ui.ConsoleIO;
//import com.epam.training.sportsbetting.ui.ConsoleReaderWriter;
//
//class AppServiceTest {
//	@Mock
//	private SportsBettingService service;
//	@Mock
//	private ConsoleIO io;
//	@InjectMocks
//	private App underTest;
//	@Mock
//	private ConsoleReaderWriter consoleReaderWriter;
//
//	@BeforeEach
//	public void init() {
//		MockitoAnnotations.initMocks(this);
//	}
//
//	@Test
//	void testPlayShouldPlaySportsBetting() {
//		Player player = new Player();
//		player.setBalance(new BigDecimal(100));
//		player.setCurrency(Currency.HUF);
//		player.setName("Pista");
//
//		when(io.readPlayerData()).thenReturn(player);
//		PrintStream output = mock(PrintStream.class);
//		System.setOut(output);
//		verify(io).readPlayerData();
//	}
//
//}