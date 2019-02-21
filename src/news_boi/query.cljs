(ns news-boi.query
	(:require
		[reagent.core :as r]
		[ajax.core :as ajax]
		[clojure.pprint :as p]
	)
)

(def search-word (r/atom "")) ;; keyword that was searched, default: bitcoin
(def content (r/atom [])) ;; array storing all the articles/tweets/etc

(defn merge-content [new-content]
	(when-not (empty? new-content)
		(->> 	(into [] new-content)
				(concat (into [] @content))
				(into [])
				(reset! content)
			)

		;; shuffle
		(partial shuffle @content)
	)
)

(defn get-content-reddit [keyword]
	;; query r/news for new
	(let [query-url (str "https://www.reddit.com/r/news/search.json?q=" keyword "&restrict_sr=1&sort=new&limit=50")]
		(ajax/GET query-url
			{:handler 	#(->> 	(get-in % [:data :children])
								(map :data)
								(merge-content) ;; merge with content
						)
			:response-format 	:json
			:keywords?			true
			}
		)
	)
	;; query r/[keyword] for top this week
	(let [query-url (str "https://www.reddit.com/r/" keyword "/top.json?t=week&limit=50")]
		(ajax/GET query-url
			{:handler 	#(->> 	(get-in % [:data :children])
								(map :data)
								(merge-content)
						)
			:response-format 	:json
			:keywords?			true
			}
		)
	)
)

(defn get-content [keyword]
	(reset! content [])
	(get-content-reddit keyword)
)