(ns sublist)

(defn determine_if_sublist [list_a list_b]
  (reduce
    (fn [acc0 start]
      (or acc0
        (reduce
          (fn [acc1 end]
            (or acc1
              (= list_a (subvec list_b start end))
            )
          ) false (range start (inc (count list_b)))
        )
      )
    ) false (range 0 (count list_b))
  )
)

(defn classify [list_a list_b]
 (cond
    (= list_a list_b) :equal
    (determine_if_sublist list_a list_b) :sublist
    (determine_if_sublist list_b list_a) :superlist
    :else :unequal
 )
)
