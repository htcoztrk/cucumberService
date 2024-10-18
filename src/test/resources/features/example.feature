Feature: Online Process
  Rule:Deneme
    Background: Dene




  Rule:RuleDeneme
    Background: Background

  Scenario: getRequest

    * get request to "posts/1"
    * check status code "200"
    * check response "body": "userId","1"
    * check response "body": "id","1"
    * check response "body": "title","sunt aut facere repellat provident occaecati excepturi optio reprehenderit"

  @Tag2
  Scenario: post
    * create JSONObject
    * put object "s": "title","egehan"
    * put object "s": "body","kömürcü"
    * put object "i": "userId","10"
    * post to "posts"
    * check status code "303"
    * check response "body": "id","101"

  @Tag3
  Scenario: post2
    * create JSONObject
    * put object "s": "title","egehan"
    * put object "s": "body","kömürcü"
    * put object "i": "userId","10"
    * post to "posts"
    * check status code "201"
    * check response "body": "id","101"
