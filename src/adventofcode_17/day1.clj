(ns adventofcode-17.day1
  (:require [clojure.java.io :as io]
            [clojure.core.match :refer [match]]
            [clojure.test :refer :all]))
(:use 'clojure.test)

; -- Rotate Right
(with-test
  (defn rot-right [data n]
     (take (count data) (drop n (cycle data))))

  (is (= [1 2 2 1] (rot-right [1 1 2 2] 1)))
  (is (= [2 2 1 1] (rot-right [1 1 2 2] 2))) )


; -- Solve the problem
(with-test
  (defn solve [data n]
    (reduce + (map (fn [x] (if (= (first x) (second x)) (first x) 0))
                   (map vector data (rot-right data n)))) )

  (is (= 3 (solve [1 1 2 2] 3)))
  (is (= 4 (solve [1 1 1 1] 1)))
  (is (= 0 (solve [1 2 3 4] 1)))
  (is (= 9 (solve [9 1 2 1 2 1 2 9] 1)))
  (is (= 6 (solve [1 2 1 2] 2)))
  (is (= 0 (solve [1 2 2 1] 2)))
  (is (= 4 (solve [1 2 3 4 2 5] 3)))
  (is (= 12 (solve [1 2 3 1 2 3] 3)))
  (is (= 4 (solve [1 2 1 3 1 4 1 5] 4))) )






(defn -main [data-file]
  (test #'rot-right)
  (test #'solve)

  (let [data (map (fn [x] (Character/digit x 10)) (seq (slurp data-file)))]
    (println "PartA: " (solve data 1))
    (println "PartB: " (solve data (/ (count data) 2)))))
