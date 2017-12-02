(ns adventofcode-17.day1
  (:require [clojure.java.io :as io] [clojure.core.match :refer [match]]))

(defn solve [raw-data]
  (defn solve' [dta]
    (loop [data dta
           res 0]

      (match [data]
             [([x y] :seq)] (if (= x y) (+ res x) res)
             [([x y & rest] :seq)] (recur (cons y rest) (if (= x y) (+ res x) res)))))

    (solve' (conj raw-data (last raw-data))))



(defn -main [data-file]
  (let [raw-data (map (fn [x] (Character/digit x 10)) (seq (slurp data-file)))]
    (println (solve raw-data))))
