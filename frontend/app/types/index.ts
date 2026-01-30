export interface Project {
    id: number;
    title: string;
    description: string;
    content: string;
    thumbnailUrl?: string;
    repoUrl?: string;
    demoUrl?: string;
    startDate: string;
    endDate?: string;
    isActive: boolean;
    techStack?: string[];
    featured?: boolean;
}

export interface Skill {
    id: number;
    name: string;
    category: 'BACKEND' | 'DEVOPS' | 'LANGUAGE';
    proficiency: number;
}

export interface Experience {
    id: number;
    companyName: string;
    role: string;
    description: string;
    startDate: string;
    endDate?: string;
    isActive: boolean;
}

export interface PersonalInfo {
    id?: number;
    name: string;
    email: string;
    phone?: string;
    bio: string;
    shortBio: string;
    githubUrl?: string;
    linkedinUrl?: string;
    blogUrl?: string;
    profileImageUrl?: string;
    resumeUrl?: string;
}


export interface Award {
    id: number;
    title: string;
    organization: string;
    date: string;
    description?: string;
}

export interface Education {
    id: number;
    institution: string;
    degree: string;
    major: string;
    gpa?: string;
    startDate: string;
    endDate: string;
    description?: string;
}

export interface Certification {
    id: number;
    name: string;
    date: string;
    organization?: string;
}

export interface LanguageSkill {
    id: number;
    name: string;
    level: string;
    date?: string;
}

export interface Activity {
    id: number;
    title: string;
    period: string;
    description: string;
    category: string;
}

export interface ProfileResponse {
    personalInfo?: PersonalInfo;
    skills: Skill[];
    featuredProjects: Project[];
    awards: Award[];
    educations: Education[];
    certifications: Certification[];
    languageSkills: LanguageSkill[];
    activities: Activity[];
}
