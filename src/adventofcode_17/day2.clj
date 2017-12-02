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


; -- Checksum
(with-test
  (defn checksum [data]
    (reduce +
            (map (fn [line] (- (apply max line) (apply min line))) data)))

  (is (= 18 (checksum [[5 1 9 5] [7 5 3] [2 4 6 8]]))))

; -- Find evenly divisible
(with-test
  (defn find-divisible [row]
    (set (map set (filter (fn [x] (= (rem (apply max x) (apply min x)) 0)) (combo/combinations row 2)))))

  (is (= #{#{2 8}} (find-divisible [5 9 2 8])))
  (is (= #{#{3 9}} (find-divisible [9 4 7 3])))
  (is (= #{#{3 6}} (find-divisible [3 8 6 5]))))

; -- Checksum2
(with-test
  (defn checksum-div [data]
    (reduce + (map (fn [row] (let [divs (first (find-divisible row))] (/ (apply max divs) (apply min divs))))
                   data)))

  (is (= 9 (checksum-div [[5 9 2 8] [9 4 7 3] [3 8 6 5]]))))


(defn -main [data-file]
  (test #'checksum)
  (test #'find-divisible)

  (println (checksum-div (parse-file data-file))))
