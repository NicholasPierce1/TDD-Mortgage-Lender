Stories and Acceptance Criteria
As a lender, I want to be able to check my available funds,
so that I know how much money I can offer for loans.

When I check my available funds
Then I should see how much funds I currently have
As a lender, I want to add money to my available funds, so that I can offer loans to potential home buyers.

Given I have <current_amount> available funds
When I add <deposit_amount>
Then my available funds should be <total>

Examples:
| current_amount | deposit_amount |   total  |
|     100,000    |      50,000    | 150,000  |
|     200,000    |      30,000    | 230,000  |
As a lender, I want to approve or deny loans base on available funds, so that I don't go bankrupt.

Given I have <available funds> in available funds
When a qualified applicant applies for <loan amount> loan
Then the loan is <loan status>

Example:
| loan amount | available funds | loan status |
|   125,000   |    100,000      |    denied   |
|   125,000   |    200,000      |  approved   |
|   125,000   |    125,000      |  approved   |
As a lender, I want to qualify loan applications, so that I can ensure I get my money back.

Rule: Qualifying candidates must have debt-to-income (DTI) less than 36%, credit score above 620 and savings worth 25% of requested loan amount.

Given a loan applicant with <dti> DTI, <credit score> credit score, and <savings> savings
When they apply for a loan
Then their qualification is <qualification>

Example:
|  loan amount  |   dti  |  credit score  |  savings  |  qualification |
|    250,000    |   21   |       700      | 100,000   |      true      |
|    250,000    |   37   |       700      | 100,000   |     false      |
|    250,000    |   30   |       600      | 100,000   |     false      |
|    250,000    |   30   |       700      |  20,000   |     false      |
As a lender, I want to keep pending loan amounts in a separate account, so I don't extend too many offers and bankrupt myself.

Given I have approved a loan
When a loan offer is sent
Then the requested loan amount is moved from available funds to pending funds
And the loan status is marked as pending
As a lender, I want to receive response for loan offers, so that I can update the status of pending loans.

Given I have sent a loan offer to a qualified applicant
When the applicant accepts my loan offer
Then the loan amount is removed from the pending funds
And the loan status is marked as accepted

Given I have sent a loan offer to a qualified applicant
When the applicant rejects my loan offer
Then the loan amount is moved from the pending funds back to available funds
And the loan status is marked as rejected
As a lender, I want to set an expiration date of 3 days on all loan contracts, so that I can manage my time and money wisely.

Given I have sent a loan offer to a qualified applicant,
When the applicant does not accept my loan contract within 3 days,
Then the loan amount is move from the pending funds back to available funds
And the loan status is marked as expired