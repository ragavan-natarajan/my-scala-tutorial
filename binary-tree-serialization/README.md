# Scalacheck with Generators and JUnit runner.

This code snippet has a trivial implementation of a binary tree serialization
and deserialization tested with the help of scalacheck and generators. 

The aim of this project is to show the power of scalacheck framework in
generating completely randomized test cases for whatever you want to write test
cases for.

In order to build, just do `sbt clean compile test assembly`

Note: `test` might sometimes throw a StackOverflowError if the randomized tree
generation became too large - but that's not a problem. Just retrying the build
would make it successful in subsequent attempts.
