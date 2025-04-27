package tsu.pod.jetbrains.bootiful

import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import kotlin.test.assertTrue

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class BootifulApplicationTests(
	@Autowired val customerRepository: CustomerRepository
) {

	@Test
	fun contextLoads() {
		runBlocking {
			val customers = customerRepository.findAll();
			assertTrue(customers.count() > 0)
		}
	}

}
