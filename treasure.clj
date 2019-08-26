(require '[clojure.string :as strLib])
(defn appendingPlusForFound 

  [charFoundMap i j]
    (def found (get charFoundMap :found))
    (def charMap (get charFoundMap :charMap))
    (def updatedStringEx (assoc (get charMap i) j "+"))
    (def updatedMap (assoc charMap i updatedStringEx))
    (def updatedCharFoundMap (assoc charFoundMap :charMap updatedMap))
    (assoc updatedCharFoundMap :found true)
)

(defn findTreasure 
  
  [charFoundMap i j rows cols]
    
    (def found (get charFoundMap :found))
    (def charMap (get charFoundMap :charMap))
    (if(= (get (get charMap i) j) "@")
    (assoc charFoundMap :found true)
    (do(def updatedStringEx (assoc (get charMap i) j "!"))
		(def updatedMap (assoc charMap i updatedStringEx))
		(def updatedCharFoundMap (assoc charFoundMap :charMap updatedMap))
		(def bool1 
				  (if(and (< j (- cols 1)) (or (= (get (get charMap i) (+ j 1)) "-") (= (get (get charMap i) (+ j 1)) "@")))
					(if(= (get (findTreasure updatedCharFoundMap i (+ j 1) rows cols):found) true)
					true
					false
					)
					false
				  )
		) 
		
		(def bool2 
				  (if(and (< i (- rows 1)) (or (= (get (get charMap (+ i 1)) j) "-") (= (get (get charMap (+ i 1)) j) "@")) (not bool1))
					(if(= (get (findTreasure updatedCharFoundMap (+ i 1) j rows cols):found) true)
					true
					false
					)
					false
				  )
		)
		(def bool3 
				  (if(and (> i 0) (or (= (get (get charMap (- i 1)) j) "-") (= (get (get charMap (- i 1)) j) "@")) (not bool1) (not bool2))
					(if(= (get (findTreasure updatedCharFoundMap (- i 1) j rows cols):found) true)
					true
					false
					)
					false
				  )
		)
		(def bool4 
				  (if(and (> j 0) (or (= (get (get charMap i) (- j 1)) "-") (= (get (get charMap i) (- j 1)) "@")) (not bool1) (not bool2) (not bool3))
					(if(= (get (findTreasure updatedCharFoundMap i (- j 1) rows cols):found) true)
					true
					false
					)
					false
				  )
		)
		
		(if(or bool1 bool2 bool3 bool4)
		(appendingPlusForFound updatedCharFoundMap i j)
		updatedCharFoundMap
		)
	)
    
    )
    
  )

(defn printArrOfArr

  [arrOfArr rows]
  (def i (atom 0))
  (while (< @i rows)
    (println (strLib/join "" (get arrOfArr @i)))
    (swap! i inc)
  )  
)

(defn initFindingTreasure

  [arrOfArrInp]
  (def rows (count arrOfArrInp))
  (def a (hash-map :found false, :charMap arrOfArrInp))
  (def outArrOfArr(findTreasure a 0 0 (count arrOfArrInp) (count (get arrOfArrInp 0))))
  (println "This is my challenge:\n")
  (printArrOfArr arrOfArrInp rows)
  (println (if(= (get outArrOfArr :found) true)
    "\nWoo hoo, I found the treasure :-)\n"
    "\nUh oh, I could not find the treasure :-(\n"
  ))
  (printArrOfArr (get outArrOfArr :charMap) rows)
  (println "")
)
(defn sameNoOfColumns

  [arrOfArrInp rows]
  (def i (atom 1))
  (def same (atom 1))
  (def countCols (count (get arrOfArrInp 0)))
  (while(< @i rows)
    (do
      (if(= (count (get arrOfArrInp @i)) countCols)
        (swap! same inc)
      )
      (swap! i inc)
    )
  )
  (if(= @same rows)
    true
    false
  )
  
)
(defn getArrOfArrInp
  
  [inpStrArr rows arrOut i]
  (if(< i rows)
    (if (>(count(strLib/trim (get inpStrArr i))) 0)
		(getArrOfArrInp inpStrArr rows (conj arrOut (vec(strLib/split (strLib/trim (get inpStrArr i)) #"")))  (inc i))
		(getArrOfArrInp inpStrArr rows arrOut (inc i))
	)	
    arrOut
  )
)

(defn startSearching
  
  [fileName]
  (def arrInp
      (vec
        (with-open [readInp (clojure.java.io/reader "map.txt")]
        (reduce conj [] (line-seq readInp)))
      )
  )
  (def arrOfArrInp(getArrOfArrInp arrInp (count arrInp) [] 0))
  (if(sameNoOfColumns arrOfArrInp (count arrOfArrInp))
    (initFindingTreasure arrOfArrInp)
    (println "\nMap is not valid\n")
  )
)
(def fileName "map.txt")
(if(.exists (clojure.java.io/file fileName))
          (startSearching fileName)
          (println "Please give map.txt")
)


