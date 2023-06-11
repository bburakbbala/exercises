using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IHospitalDepartmentRepository : IRepositoryAsync<HospitalDepartment>
    {
        void Update(HospitalDepartment hospitalDepartmant);
    }
}
