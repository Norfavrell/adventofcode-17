(ns adventofcode-17.day2
  (:require [clojure.java.io :as io]
            [clojure.core.match :refer [match]]
            [clojure.test :refer :all]
            [clojure.math.combinatorics :as combo]))

; -- Parse file
(defn parse-file [file]
  (map (fn [line]
         (map (fn [x] (Integer/parseInt x)) (clojure.string/split line #"\s+")))
       (clojure.string/split-lines (slurp file))))


; -- Find evenly divisible
(with-test
  (defn find-divisible [row]
    (set (map set (filter (fn [x] (= (rem (apply max x) (apply min x)) 0)) (combo/combinations row 2)))))

  (is (= #{#{2 8}} (find-divisible [5 9 2 8])))
  (is (= #{#{3 9}} (find-divisible [9 4 7 3])))
  (is (= #{#{3 6}} (find-divisible [3 8 6 5]))))


; -- Max-Min row checksum
(with-test
  (defn row-checksum-maxmin [row] (- (apply max row) (apply min row)))

  (is (= 8 (row-checksum-maxmin [5 1 9 5])))
  (is (= 4 (row-checksum-maxmin [7 5 3])))
  (is (= 6 (row-checksum-maxmin [2 4 6 8]))))


; -- Div row checksum
(with-test
  (defn row-checksum-div [row]
    (let [divs (first (find-divisible row))] (/ (apply max divs) (apply min divs))))

  (is (= 4 (row-checksum-div [5 9 2 8])))
  (is (= 3 (row-checksum-div [9 4 7 3])))
  (is (= 2 (row-checksum-div [3 8 6 5]))))


; -- Checksum
(with-test
  (defn checksum [row-checksum data]
    (reduce + (map row-checksum data)))

  (is (= 18 (checksum row-checksum-maxmin [[5 1 9 5] [7 5 3] [2 4 6 8]])))
  (is (= 9 (checksum row-checksum-div [[5 9 2 8] [9 4 7 3] [3 8 6 5]]))))



(defn -main [data-file]
  (test #'row-checksum-maxmin)
  (test #'find-divisible)
  (test #'row-checksum-div)
  (test #'checksum)

  (let [data (parse-file data-file)]
    (println "PartA: " (checksum row-checksum-maxmin data))
    (println "PartB: " (checksum row-checksum-div data))))
