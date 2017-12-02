(ns adventofcode-17.day1-test
  (:require [clojure.test :refer :all]
            [adventofcode-17.day1 :refer :all]))

(deftest day1-test
  (testing "Example 1"
    (is (= 3 (solve [1 1 2 2])))))

