Feature:  User can view reviews of teacher
  Scenario: User Student successfully view reviews of teachers
    Given Student with a username "jesus.student"
    When go to review option and call to api is made
    Then response status is 200
    And all review data is retrieved

  Scenario: User Teacher successfully view reviews of Teachers
    Given Teacher with a username "albert.teacher"
    When go to review option and call to api is made
    Then response status is 200
    And all review data is retrieved

  Scenario: Admin successfully view reviews of Teachers
    Given Admin with username "jose.admin"
    When go to review option and call to api is made
    Then response status is 200
    And all review data is retrieved