package br.com.rodison.emailsenderservice.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.named("EmailSenderService is ready!").up().build();
	}
}
