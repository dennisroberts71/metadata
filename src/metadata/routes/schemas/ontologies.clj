(ns metadata.routes.schemas.ontologies
  (:use [common-swagger-api.schema]
        [metadata.routes.schemas.common])
  (:require [common-swagger-api.schema.ontologies :as schema]
            [schema.core :as s]))

(s/defschema OntologyHierarchyFilterParams
  (merge StandardUserQueryParams
         schema/OntologyHierarchyFilterParams))

(s/defschema OntologySearchParams
  (merge StandardUserQueryParams
         {:label (describe String "The ontology class label search term")}))

(s/defschema OntologyDetailsList
  {:ontologies (describe [schema/OntologyDetails] "List of saved Ontologies")})

(s/defschema OntologyClass
  {:iri
   (describe String "The unique IRI for this Ontology Class")

   :label
   (describe (s/maybe String) "The label annotation of this Ontology Class")

   (s/optional-key :description)
   (describe (s/maybe String) "The description annotation of this Ontology Class")})

(s/defschema OntologyClassHierarchy
  (merge OntologyClass
         {(s/optional-key :subclasses)
          (describe [(s/recursive #'OntologyClassHierarchy)] "Subclasses of this Ontology Class")}))

(s/defschema OntologyHierarchy
  {:hierarchy (describe (s/maybe OntologyClassHierarchy) "An Ontology Class hierarchy")})

(s/defschema OntologyHierarchyList
  {:hierarchies (describe [OntologyClassHierarchy] "A list of Ontology Class hierarchies")})

(s/defschema TargetHierarchyFilterRequest
  (merge TargetItem
         {:attrs
          (describe [String] "The metadata attributes that store class IRIs for the given ontology")}))

(s/defschema OntologySearchFilterRequest
  (merge TargetFilterRequest
         {:attrs
          (describe [String] "The metadata attributes that store class IRIs for the given ontology")}))
