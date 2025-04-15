import { ApolloServer } from "@apollo/server";
import { startStandaloneServer } from "@apollo/server/standalone";
import gql from "graphql-tag";
import cors from "cors";
import express from "express";

const app = express();
app.use(cors());
const pers = [
    {
        name: "David",
        telefono: 55445544,
        calle: "Paula",
        ciudad: "Camaguey",
        id: "4das-749s-5d4a-as5d"
    },
    {
        name: "Hector",
        telefono: 18959177,
        calle: "Maria",
        ciudad: "Habana",
        id: "5s61-ht54-rhbg-efg4"
    },
    {
        name: "Juan",
        calle: "Maria",
        ciudad: "Camaguey",
        id: "f5s61-ht54-rhbg-efg4"
    }
];

const typeDefs = gql`
    type Person {
        name: String
        telefono: Int
        calle: String
        ciudad: String
        id: ID
    }

    type Query {
        personCount: Int!
        getPersonas: [Person]
        findPersona(name: String!): Person
    }
`;

const resolvers = {
    Query: {
        getPersonas: () => pers,
        personCount: () => pers.length,
        findPersona: (root,args)=>{
            return pers.find(person => person.name === args.name); 
        }
    }
}

const server = new ApolloServer({
    typeDefs,
    resolvers,
    introspection: true, // Permite ver el esquema en Apollo Sandbox
    csrfPrevention: false 
});

const { url } = await startStandaloneServer(server, {
    listen: { port: 5001 }
});

console.log(`🚀 Server ready at ${url}`);
