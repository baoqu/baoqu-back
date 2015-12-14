(defproject baoqu "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  ;; tag::dependencies[]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [funcool/catacumba "0.9.0"]
                 [org.slf4j/slf4j-simple "1.7.12"]
                 [yesql "0.5.1"]]
  ;; end::dependencies[]
  :plugins [[lein-asciidoctor "0.1.14"]]
  :asciidoctor {:sources "doc/*.adoc"
                :to-dir "target/asciidoctor/"
                :source-highlight true
                :toc :left}
  :main baoqu.app
  :profiles
  {:uberjar {:aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}})
