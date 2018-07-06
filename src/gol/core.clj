(ns gol.core
  (:require [clojure.spec.alpha :as spec]
            [expound.alpha :as expound]))

(def empty-board [[0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]
                  [0 0 0 0 0 0 0 0 0 0]])

(spec/def ::coords (spec/cat :x (set (range 0 10)) :y (set (range 0 10))))
(spec/def ::cell #{0 1})
(spec/def ::row (spec/coll-of ::cell :kind vector? :count 10))
(spec/def ::board (spec/coll-of ::row :count 10))

(spec/fdef on-board?
  :args (spec/cat :coords ::coords :board ::board)
  :ret boolean?)
(defn on-board?
  ""
  [x y board]
  (let [height (count board)
        width (count (first board))]
    (and (>= x 0) (>= y 0) (< x width) (< y height))))

(defn neighbors
  ""
  [x y]
  []
  )

(comment

  (set! spec/*explain-out* expound/printer)
  ;; Put your cursor on start of the line (after the above sexp) and type `SPC m e e`


  ;; Any live cell with two or three live neighbors lives on to the next generation.

  ;; Any live cell with more than three live neighbors dies, as if by overpopulation.

  ;; Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
  )
