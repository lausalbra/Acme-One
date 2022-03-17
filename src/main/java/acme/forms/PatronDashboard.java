package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable{

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfProposedPatronages;
	Integer						totalNumberOfAcceptedPatronages;
	Integer						totalNumberOfDeniedPatronages;

	Double						averageBudgetOfProposedPatronages;
	Double						deviationBudgetOfProposedPatronages;
	Double						minimumBudgetOfProposedPatronages;
	Double						maximumBudgetOfProposedPatronages;

	Double						averageBudgetOfAcceptedPatronages;
	Double						deviationBudgetOfAcceptedPatronages;
	Double						minimumBudgetOfAcceptedPatronages;
	Double						maximumBudgetOfAcceptedPatronages;

	Double						averageBudgetOfDeniedPatronages;
	Double						deviationBudgetOfDeniedPatronages;
	Double						minimumBudgetOfDeniedPatronages;
	Double						maximumBudgetOfDeniedPatronages;
}